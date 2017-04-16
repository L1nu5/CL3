from bottle import *
import pymongo

connection = pymongo.MongoClient('192.168.1.9',27017)
dbname = connection.test.test
#dbname.authenticate("username","password")

text =""
count =0

@route('/')
def hello():
	return template("form")

@route('/check', method="post")
def plagiarise():
	upload = request.files.get('fileupload')
	path = "E:/FinalCodes/Plag/Plag/data/" + upload.filename
	upload.save(path)

	fout = open("temp.txt", "w")

	content = dbname.find()
	for doc in content:
		fout.write(doc['text'])
		#print ("***************")
		#print doc['text']
	
	fout.close()

	f1 = open(path, "r").read()
	f2 = open("temp.txt", "r").read()



	l1 = f1.split('.')
	l2 = f2.split('.')
	plagstr = []
	count = 0
	for line1 in l1:
		count = count + 1 	
		for line2 in l2:
			if line1 == line2:
				plagstr.append(line1)
				
	print l1
	print l2
	print len(plagstr)
	print len(l2)
	result =float((((len(plagstr)-1) *100)/(len(l2)-1)))
	print result
	return template("result", text='.'.join(plagstr), text1 = result)

run(host = '0.0.0.0', port = 8080, debug="True")