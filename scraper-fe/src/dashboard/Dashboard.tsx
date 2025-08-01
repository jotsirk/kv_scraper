import * as React from 'react';
import {useState} from 'react';

import AppTheme from '../shared-theme/AppTheme';
import {
    chartsCustomizations,
    dataGridCustomizations,
    datePickersCustomizations,
    treeViewCustomizations
} from "./theme/customizations";
import CssBaseline from "@mui/material/CssBaseline";
import Box from '@mui/material/Box';
import Stack from '@mui/material/Stack';
import SideMenu from "./components/SideMenu";
import AppNavbar from "./components/AppNavbar";
import {alpha} from '@mui/material';
import Header from './components/Header';
import MainGrid from './components/MainGrid';
import { Outlet } from 'react-router-dom';

const xThemeComponents = {
  ...chartsCustomizations,
  ...dataGridCustomizations,
  ...datePickersCustomizations,
  ...treeViewCustomizations,
};

export default function Dashboard(props: { disableCustomTheme?: boolean }) {
    const [selectedIndex, setSelectedIndex] = useState(0)

    const renderHeader = () => {
        switch (selectedIndex) {
            case 0:
                return <Header title="All Ticks"/>
            case 1:
                return <Header title="Add Tick"/>
            default:
                return <Header title="Dashboard"/>
        }
    }

  return (
    <AppTheme {...props} themeComponents={xThemeComponents}>
        <CssBaseline enableColorScheme/>
        <Box sx={{display: 'flex'}}>
            <SideMenu />
            <AppNavbar />
        <Box
          component="main"
          sx={(theme) => ({
            flexGrow: 1,
            backgroundColor: theme.vars
              ? `rgba(${theme.vars.palette.background.defaultChannel} / 1)`
              : alpha(theme.palette.background.default, 1),
            overflow: 'auto',
          })}
        >
          <Stack
            spacing={2}
            sx={{
              alignItems: 'center',
              mx: 3,
              pb: 5,
              mt: {xs: 8, md: 0},
            }}
          >
              { renderHeader() }
              <Outlet />
          </Stack>
        </Box>
      </Box>
    </AppTheme>
  );
}
