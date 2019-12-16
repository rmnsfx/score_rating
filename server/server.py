
import socket
import threading
import time
import wx
import psycopg2




host = '192.168.0.6'
port = 5000
conn = None

app = wx.App()
frame = wx.Frame(None, title='Simple application')
frame.Center()
panel = wx.Panel(frame, wx.ID_ANY)
text = wx.TextCtrl(panel, pos=(10, 150), size=(600, 50), style=wx.TE_MULTILINE|wx.TE_READONLY)





def insert_artist(artist_name):
    """ insert a new artist into the table """
    sql = """INSERT INTO score_table(artist_id)
             VALUES(%s);"""
    pg_conn = None
    #vendor_id = None

    try:
        # read database configuration
        params = config()
        # connect to the PostgreSQL database
        pg_conn = psycopg2.connect(host="localhost", database="score", user="postgres", password="123")
        # create a new cursor
        cur = pg_conn.cursor()
        # execute the INSERT statement
        cur.execute(sql, (vendor_name,))
        # get the generated id back
        #vendor_id = cur.fetchone()[0]
        # commit the changes to the database
        conn.commit()
        # close communication with the database
        cur.close()
    except (Exception, psycopg2.DatabaseError) as error:
        print(error)
    finally:
        if pg_conn is not None:
            pg_conn.close()

    #return vendor_id


def onButton1(event):

    text.Clear()
    text.AppendText("button1")

    if conn != None:
        conn.send("kadr1/n")

    png1 = wx.Image("image/img1.png", wx.BITMAP_TYPE_ANY).ConvertToBitmap()
    wx.StaticBitmap(panel, -1, png1, (100, 500), (png1.GetWidth(), png1.GetHeight()))

    frame.Refresh()


def onButton2(event):

    text.Clear()
    text.AppendText("button2")

    if conn != None:
        conn.send("kadr2/n")

    png2 = wx.Image("image/img2.png", wx.BITMAP_TYPE_ANY).ConvertToBitmap()
    wx.StaticBitmap(panel, -1, png2, (100, 500), (png2.GetWidth(), png2.GetHeight()))

    frame.Refresh()

def onButton3(event):

    text.Clear()
    text.AppendText("button3")

    if conn != None:
        conn.send("kadr3/n")


    png3 = wx.Image("image/img1.png", wx.BITMAP_TYPE_ANY).ConvertToBitmap()
    wx.StaticBitmap(panel, -1, png3, (100, 500), (png3.GetWidth(), png3.GetHeight()))

    frame.Refresh()

def onButton4(event):

    text.Clear()
    text.AppendText("button4")

    if conn != None:
        conn.send("kadr4/n")


    png4 = wx.Image("image/img1.png", wx.BITMAP_TYPE_ANY).ConvertToBitmap()
    wx.StaticBitmap(panel, -1, png4, (100, 500), (png4.GetWidth(), png4.GetHeight()))

    frame.Refresh()


def handle(conn, addr):
    print("Connected by: ", addr)

    while True:
        data = conn.recv(16)
        print(data)

        if "artist1" in data:
            insert_artist("artist1")
        if "artist2" in data:
            insert_artist("artist2")
        if "artist3" in data:
            insert_artist("artist3")
        if "artist4" in data:
            insert_artist("artist4")

def interface():

    #app = wx.App()
    #frame = wx.Frame(None, title='Simple application')
    #frame.Center()

    #panel = wx.Panel(frame, wx.ID_ANY)
    button1 = wx.Button(panel, wx.ID_ANY, 'Кадр 1', (10, 10))
    button1.Bind(wx.EVT_BUTTON, onButton1)

    button2 = wx.Button(panel, wx.ID_ANY, 'Кадр 2', (120, 10))
    button2.Bind(wx.EVT_BUTTON, onButton2)

    button3 = wx.Button(panel, wx.ID_ANY, 'Кадр 3', (240, 10))
    button3.Bind(wx.EVT_BUTTON, onButton3)

    button4 = wx.Button(panel, wx.ID_ANY, 'Голосование', (10, 50))
    button4.Bind(wx.EVT_BUTTON, onButton4)

    #text = wx.TextCtrl(panel, pos=(10, 150), size=(600, 50), style=wx.TE_MULTILINE|wx.TE_READONLY)
    #text.SetForegroundColour("black")




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

