import List from '@mui/material/List'
import Stack from '@mui/material/Stack'
import {useEffect, useState} from 'react'
import {getAllTicks} from '../services/ticksService'
import {PropertyTick} from '../types/tick'
import ListItem from "@mui/material/ListItem";
import Card from '@mui/material/Card'

export default function TicksList() {
  const [ticks, setTicks] = useState<PropertyTick[]>([])
  const [error, setError] = useState(null)

  useEffect(() => {
    getAllTicks()
      .then((data: PropertyTick[]) => {
        return setTicks(data);
      })
      .catch(err => setError(err.message))
  }, [])

  if (error) return <div>Error: {error}</div>
  if (!ticks.length) return <div>Loading ticks…</div>

  return (
    <Stack sx={{flexGrow: 1, p: 1, justifyContent: 'space-between'}}>
      <List>
        {ticks.map(tick => (
          <ListItem key={tick.id}>
            <Card>
              {tick.propertyKey}
            </Card>
          </ListItem>
        ))}
      </List>
    </Stack>
  )
}
