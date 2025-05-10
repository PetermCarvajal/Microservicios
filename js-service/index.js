const express = require('express');
const app = express();
const port = 8083;

const cors = require('cors');
app.use(cors());

app.use(express.json());

app.get('/test', (req, res) => {
  res.json({ message: 'Microservicio Node.js activo ✅' });
});

app.listen(port, () => {
  console.log(`Microservicio JS escuchando en http://localhost:${port}`);
});
