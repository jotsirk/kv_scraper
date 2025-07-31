import React from 'react';
import AddTickForm from '../components/AddTickForm/AddTickForm';
import { useNavigate } from 'react-router-dom';

export default function AddTick() {
  const navigate = useNavigate();

  return (
    <div>
      <h1>Add New Tick</h1>
      <AddTickForm onSaved={() => navigate('/')} />
    </div>
  );
}
