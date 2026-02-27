import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import AppRoutes from "./routes/AppRoutes.jsx";
import {AuthProvider} from "./auth/AuthContext.jsx";
import "./global.css"

createRoot(document.getElementById('root')).render(
  <StrictMode>
      <AuthProvider>
          <AppRoutes />
      </AuthProvider>
  </StrictMode>,
)
