import * as React from 'react';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Stack from '@mui/material/Stack';
import HomeRoundedIcon from '@mui/icons-material/HomeRounded';
import {MenuContentProps} from "../../types/dashboard";
import {Link, useLocation } from 'react-router-dom';

export interface MenuItem {
  text: string;
  icon: React.ReactNode;
  path: string;
}

export const mainListItems: MenuItem[] = [
  { text: 'Home',     icon: <HomeRoundedIcon />, path: '/' },
  { text: 'Add tick', icon: <HomeRoundedIcon />, path: '/add-tick' },
  { text: 'Add todo item', icon: <HomeRoundedIcon />, path: '/add-todo-item' },
  { text: 'Todo', icon: <HomeRoundedIcon />, path: '/todo' },
];

export default function MenuContent() {
  const { pathname } = useLocation();

  return (
    <Stack sx={{ flexGrow: 1, p: 1, justifyContent: 'space-between' }}>
      <List dense>
        {mainListItems.map((item) => {
          const isSelected = pathname === item.path;
          return (
            <ListItem key={item.path} disablePadding sx={{ display: 'block' }}>
              <ListItemButton
                component={Link}
                to={item.path}
                selected={isSelected}
                sx={{
                  '&.Mui-selected': { bgcolor: 'action.selected' },
                }}
              >
                <ListItemIcon>{item.icon}</ListItemIcon>
                <ListItemText primary={item.text} />
              </ListItemButton>
            </ListItem>
          );
        })}
      </List>
    </Stack>
  );
}
