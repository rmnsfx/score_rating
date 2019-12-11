
import socket
import threading
import time

import wx

app = wx.App()

frame = wx.Frame(None, title='Simple application')
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

