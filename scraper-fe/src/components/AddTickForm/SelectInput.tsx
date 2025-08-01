import React from 'react';

export default function SelectInput({ label, options, value, onChange }) {
  return (
    <div>
      <label>{label}</label>
      <select value={value} onChange={e => onChange(e.target.value)}>
        <option value="">-- Select --</option>
        {Object.entries(options).map(([key, display]) => (
          <option key={key} value={key}>
            {display}
          </option>
        ))}
      </select>
    </div>
  );
}
