import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import TicksList from "./pages/TicksList";
import AddTick from "./pages/AddTick";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<TicksList />} />
        <Route path="/add-tick" element={<AddTick />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
