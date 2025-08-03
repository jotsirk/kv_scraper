import React, {useState} from 'react';
import {Alert, Box, Button, Snackbar, TextField, Typography} from '@mui/material';
import {useNavigate} from 'react-router-dom';
import {createTodoItem} from "../services/TodoItemService";

export default function AddTodoItem() {
  const [task, setTask] = useState<string>('');
  const [notif, setNotif] = useState<{ message: string; severity: 'success' | 'error' } | null>(null);
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await createTodoItem(task);
      setNotif({message: 'To-do added!', severity: 'success'});
      setTimeout(() => navigate('/todos'), 500);
    } catch (err: any) {
      setNotif({message: err.message, severity: 'error'});
    }
  };

  return (
    <Box
      component="form"
      onSubmit={handleSubmit}
      sx={{p: 2, maxWidth: 400, mx: 'auto', display: 'flex', flexDirection: 'column', gap: 2}}
    >
      <Typography variant="h5" component="h1">
        Add New To-Do
      </Typography>

      <TextField
        label="Description"
        value={task}
        onChange={(e) => setTask(e.target.value)}
        required
        fullWidth
      />

      <Button
        type="submit"
        variant="contained"
        disabled={!task.trim()}
      >
        Save
      </Button>

      <Snackbar
        open={!!notif}
        autoHideDuration={3000}
        onClose={() => setNotif(null)}
        anchorOrigin={{vertical: 'bottom', horizontal: 'center'}}
      >
        {notif
          ? (
            <Alert
              severity={notif.severity}
              onClose={() => setNotif(null)}
            >
              {notif.message}
            </Alert>
          )
          : undefined}
      </Snackbar>
    </Box>
  );
}
