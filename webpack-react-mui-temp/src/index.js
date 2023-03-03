import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';

import React from "react";
import { createRoot } from "react-dom/client";
import Hello from "./Hello";

const container = document.getElementById("root");
const root = createRoot(container);
root.render(<Hello />);