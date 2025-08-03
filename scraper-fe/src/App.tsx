import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import AddTick from "./pages/AddTick";
import MainGrid from './dashboard/components/MainGrid';
import Dashboard from './dashboard/Dashboard';
import AddTodoItem from "./pages/AddTodoItem";
import TodoList from "./pages/TodoList";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Dashboard />}>
          <Route index element={<MainGrid />} />
          <Route path="add-tick" element={<AddTick />} />
          <Route path="add-todo-item" element={<AddTodoItem />} />
          <Route path="todo" element={<TodoList />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
