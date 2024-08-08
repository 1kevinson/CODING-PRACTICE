const express = require('express');
const router = express.Router();

// Basic route
router.get('/', (req, res) => {
    console.log("Received a request to the root endpoint");
    res.send("Hello, Express Node.js!");
});

module.exports = router;