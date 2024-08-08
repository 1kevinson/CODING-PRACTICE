const express = require('express');
const routes = require('./routes');

const app = express();
const PORT = 3000;

// Middleware
app.use(express.json());
app.use(routes);

// Starting the server
app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});

module.exports = app; // Export app for testing
