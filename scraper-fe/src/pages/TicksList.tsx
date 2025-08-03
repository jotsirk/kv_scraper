import List from '@mui/material/List'
import Stack from '@mui/material/Stack'
import {useEffect, useState} from 'react'
import {getAllTicks} from '../services/ticksService'
import {PropertyTick} from '../types/tick'
import ListItem from "@mui/material/ListItem";
import Card from '@mui/material/Card'
import CardMedia from '@mui/material/CardMedia'
import {Box, CardContent, Chip, Link, Typography} from '@mui/material'
import {CartesianGrid, Line, LineChart, ResponsiveContainer, Tooltip, XAxis, YAxis} from 'recharts';

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

  if (error) return <Typography color="error">Error: {error}</Typography>;
  if (!ticks.length) return <Typography>Loading ticks…</Typography>;

  console.log(window.location.hostname);

  return (
    <Stack sx={{flexGrow: 1, p: 1}} spacing={2}>
      <List>
        {ticks.map(tick => {

          const chartData = [...tick.logs]
            .sort((a, b) => new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime())
            .map(log => ({
              date: new Date(log.createdAt).toLocaleDateString(),
              price: log.price
            }));

          let latestPrice = 0;
          if (tick.logs.length > 0) {
            const latestLog = tick.logs.reduce((prev, curr) =>
              curr.id > prev.id ? curr : prev
            );
            latestPrice = latestLog.price;
          }

          const borderColor = tick.isReserved
            ? 'warning.main'
            : tick.isFinished
              ? 'success.main'
              : 'divider';

          return (
            <ListItem key={tick.id} disableGutters>
              <Card sx={{display: 'flex', width: '100%', border: 1, borderColor}}>
                <CardMedia
                  component="img"
                  sx={{width: 160, height: 120, objectFit: 'cover'}}
                  image={tick.imageUrl}
                  alt={tick.propertyKey}
                />
                <Box sx={{display: 'flex', flexDirection: 'column', flex: 1}}>
                  <CardContent sx={{flex: '1 0 auto'}}>
                    <Box sx={{display: 'flex', alignItems: 'center', justifyContent: 'space-between'}}>
                      <Link
                        href={tick.url}
                        target="_blank"
                        rel="noopener noreferrer"
                        underline="hover"
                        color="textPrimary"
                      >
                        <Typography variant="h6" component="span">
                          {tick.propertyKey}
                        </Typography>
                      </Link>
                      <Typography variant="subtitle2" component="span" sx={{ ml: 2 }}>
                        (Latest: ${latestPrice.toFixed(2)})
                      </Typography>
                      <Box sx={{display: 'flex', gap: 1}}>
                        {tick.isReserved && <Chip label="Reserved" color="warning" size="small"/>}
                        {tick.isFinished && <Chip label="Finished" color="success" size="small"/>}
                        {!tick.isReserved && !tick.isFinished && <Chip label="Available" size="small"/>}
                      </Box>
                    </Box>
                    {chartData.length > 0 ? (
                      <Box sx={{width: '100%', height: 200, mt: 2}}>
                        <ResponsiveContainer>
                          <LineChart
                            data={chartData}
                            margin={{top: 10, right: 30, bottom: 50, left: 0}}
                          >
                            <CartesianGrid strokeDasharray="3 3"/>
                            <XAxis
                              dataKey="date"
                              tick={{fontSize: 10}}
                              angle={-45}
                              textAnchor="end"
                              interval={0}
                              height={60}
                            />
                            <YAxis tick={{fontSize: 10}}/>
                            <Tooltip formatter={(value: number) => `$${value.toFixed(2)}`}/>
                            <Line
                              type="monotone"
                              dataKey="price"
                              stroke="#1976d2"
                              strokeWidth={2}
                              dot={{r: 3}}
                              activeDot={{r: 5}}
                            />
                          </LineChart>
                        </ResponsiveContainer>
                      </Box>
                    ) : (
                      <Typography variant="body2" color="textSecondary" sx={{mt: 1}}>
                        No logs available
                      </Typography>
                    )}
                  </CardContent>
                </Box>
              </Card>
            </ListItem>
          );
        })}
      </List>
    </Stack>
  );
}
