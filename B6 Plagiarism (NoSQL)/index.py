from bottle import *
import pymongo

text = ""
con = pymongo.MongoClient('localhost',27017)
dbName = con.plagarize
colName = dbName.plagarize

@route('/')
def hello():
    return template('form')

@route('/check',method='post')
def checkPlag():
	upload = request.files.get('fileupload')
	path = "D:/data/"+upload.filename
	upload.save(path)

	fout = open("temp.txt","w")
	plagstr = []
	coll = colName.find()
	for doc in coll:
		#print(doc['text'])
		fout.write(doc['text'])
	fout.close()

	file1 = open(path,"r").read()
	file2 = open("temp.txt","r").read()

	lines1 = file1.split('.')
	lines2 = file2.split('.')

	print("**********************")
	for line1 in lines1:
		for line2 in lines2:
			if line1 == line2:
				plagstr.append(line2)
				print(plagstr)

	return template("result",text=''.join(plagstr))
	
run(host='0.0.0.0', port=8080, debug=True)