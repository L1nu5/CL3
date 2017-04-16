from bitstring import BitArray
from flask import Flask
from flask import request
from flask import render_template
app = Flask(__name__)

def booth(m,r,x,y):
	totalLength=x+y+1
	mA=BitArray(int=m,length=totalLength)
	print mA
	A=mA<<(y+1)
	print A
	mA1=BitArray(int=-m,length=totalLength)
	S=mA1<<(y+1)
	P1=BitArray(int=r,length=y)
	P1.prepend(BitArray(int=0,length=x))
	P=P1 << (1)
	print "A : ",A.bin
	print "S : ",S.bin
 
	for i in range(1,y+1):
		if P[-2:] == '0b01':
			P=BitArray(int=P.int+A.int,length=totalLength)
		elif P[-2:] == '0b10':
			P=BitArray(int=P.int+S.int,length=totalLength)
		P=BitArray(int=(P.int >>1),length=P.len)
	P = P[:-1]
	print "P : ",P.bin 
	return P.bin,P.int


@app.route('/')
def f():
	return render_template("index.html")

@app.route('/',methods=['POST'])
def g():
	text1 = int(request.form['text1'])
	text2 = int(request.form['text2'])
	n,m=booth(text1,text2,8,8)
	return "Answer in binary: "+str(n)+"<br>Answer: "+str(m)
   
if __name__ == '__main__':
	app.run('localhost',debug=True)


'''OUTPUT
[akmyths@localhost ~]$ cd BE/CL3/A3
[akmyths@localhost A3]$ ls
A3.py  templates  test.py
[akmyths@localhost A3]$ python A3.py 
 * Running on http://localhost:5000/ (Press CTRL+C to quit)
 * Restarting with stat
 * Debugger is active!
 * Debugger PIN: 136-155-315
127.0.0.1 - - [05/Apr/2017 23:29:05] "GET / HTTP/1.1" 200 -
127.0.0.1 - - [05/Apr/2017 23:29:05] "GET /favicon.ico HTTP/1.1" 404 -
127.0.0.1 - - [05/Apr/2017 23:29:05] "GET /favicon.ico HTTP/1.1" 404 -
0b00000000000000011
0b00000011000000000
A :  00000011000000000
S :  11111101000000000
P :  0000000000001100
127.0.0.1 - - [05/Apr/2017 23:29:12] "POST / HTTP/1.1" 200 -

'''
