
import socket
import threading
import time
import wx



host = '192.168.0.6'
port = 5000


def onButton(event):
    print("Button pressed")

def handle(conn, addr):
    print("Connected by: ", addr)

    while True:
        data = conn.recv(100)
        print(data)

def interface():

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

    frame.Maximize(True)
    frame.Show()
    app.MainLoop()



if __name__ == "__main__":

    interface()

    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    try:
        s.bind((host, port))
    except socket.error as msg:
        print(str(msg))

    s.listen(30)


    while True:

        conn, addr = s.accept()
        threading.Thread(target=handle, args=(conn, addr,)).start()

    conn.close()

