import React, {useEffect, useState} from 'react'
import {
  Checkbox,
  Divider,
  List,
  ListItem,
  ListItemIcon,
  ListItemText,
  Typography,
  Box,
  Snackbar,
  Alert,
} from '@mui/material'
import {TodoItem} from '../types/todo'
import {
  finishTodoItem,
  getFinishedTodoItems,
  getUnfinishedTodoItems,
  unfinishTodoItem
} from "../services/TodoItemService";

export default function TodoList() {
  const [unfinished, setUnfinished] = useState<TodoItem[]>([])
  const [finished, setFinished] = useState<TodoItem[]>([])
  const [error, setError] = useState<string | null>(null)
  const [notif, setNotif] = useState<{ message: string; severity: 'success' | 'error' } | null>(null)

  const loadItems = async () => {
    try {
      const [un, fin] = await Promise.all([
        getUnfinishedTodoItems(),
        getFinishedTodoItems(),
      ])
      setUnfinished(un)
      setFinished(fin)
    } catch (e: any) {
      setError(e.message)
    }
  }

  useEffect(() => {
    loadItems()
  }, [])

  const handleFinish = async (id: number) => {
    try {
      await finishTodoItem(id)
      setNotif({message: 'Marked done', severity: 'success'})
      await loadItems()
    } catch (e: any) {
      setNotif({message: e.message, severity: 'error'})
    }
  }

  const handleUnfinish = async (id: number) => {
    try {
      await unfinishTodoItem(id)
      setNotif({message: 'Marked undone', severity: 'success'})
      await loadItems()
    } catch (e: any) {
      setNotif({message: e.message, severity: 'error'})
    }
  }

  return (
    <Box sx={{p: 2}}>
      {error && <Typography color="error">Error: {error}</Typography>}

      <Typography variant="h5" gutterBottom>
        To Do
      </Typography>
      <List>
        {unfinished.map(item => (
          <ListItem key={item.id}>
            <ListItemIcon>
              <Checkbox
                edge="start"
                checked={false}
                onChange={() => handleFinish(item.id)}
              />
            </ListItemIcon>
            <ListItemText primary={item.task}/>
          </ListItem>
        ))}
        {unfinished.length === 0 && (
          <Typography variant="body2" color="textSecondary" sx={{pl: 4}}>
            Nothing left to do!
          </Typography>
        )}
      </List>

      <Divider sx={{my: 3}}/>

      <Typography variant="h5" gutterBottom>
        Done
      </Typography>
      <List>
        {finished.map(item => (
          <ListItem key={item.id}>
            <ListItemIcon>
              <Checkbox
                edge="start"
                checked
                onChange={() => handleUnfinish(item.id)}
              />
            </ListItemIcon>
            <ListItemText
              primary={item.task}
              primaryTypographyProps={{style: {textDecoration: 'line-through'}}}
            />
          </ListItem>
        ))}
        {finished.length === 0 && (
          <Typography variant="body2" color="textSecondary" sx={{pl: 4}}>
            No completed items yet.
          </Typography>
        )}
      </List>

      <Snackbar
        open={!!notif}
        autoHideDuration={3000}
        onClose={() => setNotif(null)}
        anchorOrigin={{vertical: 'bottom', horizontal: 'center'}}
      >
        {notif
          ? (
            <Alert severity={notif.severity} onClose={() => setNotif(null)}>
              {notif.message}
            </Alert>
          )
          : undefined}
      </Snackbar>
    </Box>
  )
}
