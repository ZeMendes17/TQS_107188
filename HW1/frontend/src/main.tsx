import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import Trips from './pages/Trips.tsx';
import Pay from './pages/Pay.tsx';
import Success from './pages/Success.tsx';
import Reservations from './pages/Reservations.tsx';
import Failure from './pages/Failure.tsx';

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
  },
  {
    path: "/trips",
    element: <Trips />,
  },
  {
    path: "/pay",
    element: <Pay />,
  },
  {
    path: "/success",
    element: <Success />,
  },
  {
    path: "/reservations",
    element: <Reservations />,
  },
  {
    path: "/failure",
    element: <Failure />,
  
  },
  {
    path: "*",
    element: <div>Not Found</div>,
  },
]);

ReactDOM.createRoot(document.getElementById("root")!).render(
    <RouterProvider router={router} />
);
