import {useEffect, useState} from 'react'
import {getAllTicks} from '../services/ticksService'

export default function TicksList() {
  const [ticks, setTicks] = useState([])
  const [error, setError] = useState(null)

  useEffect(() => {
    getAllTicks()
      .then(data => {
        console.log(data);
        return setTicks(data);
      })
      .catch(err => setError(err.message))
  }, [])

  if (error) return <div>Error: {error}</div>
  if (!ticks.length) return <div>Loading ticks…</div>

  return (
    <ul>
      {ticks.map(tick => (
        <li key={tick.id}>{tick.propertyKey}</li>
      ))}
    </ul>
  )
}
