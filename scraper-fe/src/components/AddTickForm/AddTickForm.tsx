import React, { useState, FormEvent } from 'react';
import {
  TextField,
  Button,
  Box,
  Stack,
  Alert,
  FormControl,
  InputLabel,
  Select,
  MenuItem
} from '@mui/material';
import { propertyOriginType } from '../../constants/enums';
import { createTick } from '../../services/ticksService';

interface AddTickFormProps {
  onSaved?: () => void;
}

interface FormStatus {
  success: boolean;
  message: string;
}

export default function AddTickForm({ onSaved }: AddTickFormProps) {
  const [origin, setOrigin] = useState<string>('');
  const [propertyKey, setPropertyKey] = useState<string>('');
  const [imageUrl, setImageUrl] = useState<string>('');
  const [status, setStatus] = useState<FormStatus | null>(null);

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    try {
      await createTick({ origin, propertyKey, imageUrl });
      setStatus({ success: true, message: 'Tick saved!' });
      setOrigin('');
      setPropertyKey('');
      setImageUrl('');
      onSaved?.();
    } catch (err: unknown) {
      const message = err instanceof Error ? err.message : 'Unknown error';
      setStatus({ success: false, message });
    }
  };

  return (
    <Box component="form" onSubmit={handleSubmit} sx={{ width: 400, mx: 'auto', mt: 2 }}>
      <Stack spacing={2}>
        <FormControl fullWidth required>
          <InputLabel id="origin-label">Property Origin Type</InputLabel>
          <Select
            labelId="origin-label"
            id="origin-select"
            value={origin}
            label="Property Origin Type"
            onChange={e => setOrigin(e.target.value as string)}
          >
            {Object.entries(propertyOriginType).map(([key, label]) => (
              <MenuItem key={key} value={key}>{label}</MenuItem>
            ))}
          </Select>
        </FormControl>
        <TextField
          label="Property Key"
          value={propertyKey}
          onChange={e => setPropertyKey(e.target.value)}
          required
          fullWidth
        />
        <TextField
          label="Image URL"
          value={imageUrl}
          onChange={e => setImageUrl(e.target.value)}
          required
          fullWidth
        />
        <Button type="submit" variant="contained" color="primary">
          Save
        </Button>
        {status && (
          <Alert severity={status.success ? 'success' : 'error'}>
            {status.message}
          </Alert>
        )}
      </Stack>
    </Box>
  );
}
