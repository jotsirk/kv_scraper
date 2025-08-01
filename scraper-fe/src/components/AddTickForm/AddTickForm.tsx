import React, { useState } from 'react';
import { propertyOriginType } from '../../constants/enums';
import { createTick } from '../../services/ticksService';
import SelectInput from "./SelectInput";

export default function AddTickForm({ onSaved }) {
  const [origin, setOrigin] = useState('');
  const [propertyKey, setPropertyKey] = useState('');
  const [status, setStatus] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await createTick({ origin, propertyKey });
      setStatus({ success: true, message: 'Tick saved!' });
      setOrigin('');
      setPropertyKey('');
      if (onSaved) onSaved();
    } catch (err) {
      setStatus({ success: false, message: err.message });
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <SelectInput
        label="Property Origin Type"
        options={propertyOriginType}
        value={origin}
        onChange={setOrigin}
      />
      <div>
        <label>Property Key</label>
        <input
          type="text"
          value={propertyKey}
          onChange={e => setPropertyKey(e.target.value)}
        />
      </div>
      <button type="submit">Save</button>
      {status && (
        <div style={{ color: status.success ? 'green' : 'red' }}>
          {status.message}
        </div>
      )}
    </form>
  );
}
