// main script file for Solitaire
var game = new MainPanel();
startEventListeners();


function startEventListeners() {
  document.addEventListener('mousedown', (e) => mouseDownCallback(e));
  document.addEventListener('mousemove', (e) => mouseMoveCallback(e));
  document.addEventListener('mouseup',   (e) => mouseUpCallback(e));
}