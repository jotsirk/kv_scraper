import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import AddTick from "./pages/AddTick";
import MainGrid from './dashboard/components/MainGrid';
import Dashboard from './dashboard/Dashboard';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Dashboard />}>
          <Route index element={<MainGrid />} />
          <Route path="add-tick" element={<AddTick />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
