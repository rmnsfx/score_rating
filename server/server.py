
import socket
import threading
import time
import wx






def onButton(event):
    print("Button pressed")


app = wx.App()
frame = wx.Frame(None, title='Simple application')
frame.Center()

panel = wx.Panel(frame, wx.ID_ANY)
button1 = wx.Button(panel, wx.ID_ANY, 'Кадр 1', (10, 10))
button1.Bind(wx.EVT_BUTTON, onButton)

button2 = wx.Button(panel, wx.ID_ANY, 'Кадр 2', (120, 10))
button2.Bind(wx.EVT_BUTTON, onButton)

button3 = wx.Button(panel, wx.ID_ANY, 'Кадр 3', (240, 10))
button3.Bind(wx.EVT_BUTTON, onButton)

button4 = wx.Button(panel, wx.ID_ANY, 'Голосование', (10, 50))
button4.Bind(wx.EVT_BUTTON, onButton)



frame.Show()
app.MainLoop()


def handle(conn_addr):
  print("Someone Connected")
  time.sleep(1000)
  print("EXIT")

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

host = '192.168.1.2'
port = 5000

try:
    s.bind((host, port))
except socket.error as msg:
    print(str(msg))

s.listen(10)





while True:
    threading.Thread(target=handle, args=(s.accept(),)).start()

s.close()

