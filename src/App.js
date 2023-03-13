import React, { useState } from 'react';

function App() {
  const [imageSrc, setImageSrc] = useState(null);
  const [text, setText] = useState('');

  const generateQRCode = () => {
    fetch(`/qrcode?text=${text}`)
        .then((response) => response.arrayBuffer())
        .then((buffer) => {
          const base64 = btoa(new Uint8Array(buffer).reduce((data, byte) => data + String.fromCharCode(byte), ''));
          const src = `data:image/png;base64,${base64}`;
          setImageSrc(src);
        })
        .catch((error) => {
          console.error(error);
        });
  };

  return (
      <div>
        <input type="text" value={text} onChange={(e) => setText(e.target.value)} />
        <button onClick={generateQRCode}>Generate QR code</button>
        {imageSrc && <img src={imageSrc} alt="QR code" />}
      </div>
  );
}

export default App;
