import apiClient from "../apiClient"
import { TodoItem } from "../types/todo"

export const createTodoItem = (task: string): Promise<TodoItem> =>
  apiClient
    .post<TodoItem>('/todo-items', { task })
    .then(res => res.data)

export const getAllTodoItems = (): Promise<TodoItem[]> =>
  apiClient
    .get<TodoItem[]>('/todo-items')
    .then(res => res.data)

export const getUnfinishedTodoItems = (): Promise<TodoItem[]> =>
  apiClient
    .get<TodoItem[]>('/todo-items/unfinished')
    .then(res => res.data)

export const getFinishedTodoItems = (): Promise<TodoItem[]> =>
  apiClient
    .get<TodoItem[]>('/todo-items/finished')
    .then(res => res.data)

export const finishTodoItem = (id: number): Promise<void> =>
  apiClient.put<void>(`/todo-items/${id}/finish`).then(() => {})

export const unfinishTodoItem = (id: number): Promise<void> =>
  apiClient.put<void>(`/todo-items/${id}/unfinish`).then(() => {})
