from flask import Flask, render_template, request


app = Flask(__name__)

#all print statements will show the result in terminal

#open home.html when the app starts
@app.route("/")
def home():
 return render_template("home.html")
 

#this function will be executed after clicking submit button on home.html 
@app.route("/detect_plagiarism", methods=["POST"])
def detect_plagiarism():
 text = request.form.get("text")
 print text
 result = plagiarism_report_generator(text)
 return render_template("result.html", res=result)
 


def plagiarism_report_generator(text):
 
 #reading files
 fd1 = open("static/input1.txt", "r")
 fd2 = open("static/input2.txt", "r")
 
 #combining the two files into source
 source = fd1.readlines()
 source.extend(fd2.readlines())
 source = ''.join(source)
 
 #splitting the sentences with .
 text = text.split(".")
 source = source.split(".")
 
 print "\n\n\nTEXT : ", text
 print "\n\n\nSOURCE : ", source
 
 ip_lines = []
 src_lines = []
 
 for line in text:
  #remove the first space character in the sentence
  if line[:1] == " ":
   line = line[1:]
  #replace /n by null
  line = line.replace("\n","")
  #if line itself is null then ignore it
  if line == "":
   pass
  #add line into the ip_lines list
  else:
   ip_lines.append(line)
   
   
 for line in source:
  #remove the first space character in the sentence
  if line[:1] == " ":
   line = line[1:]
  #replace /n by null
  line = line.replace("\n","")
  #if line itself is null then ignore it
  if line == "":
   pass
  #add line into the ip_lines list
  else:
   src_lines.append(line)
   
   
 counter = 0 #will contain the number of repeated lines
 
   
 print "\n\n\n TEXT : ", ip_lines
 print "\n\n\n SOURCE : ", src_lines
 
 #compare every line in source with every line in input text
 for src in src_lines :
  for line in ip_lines :
   if src == line :
    counter+=1
    print line, " is repeated"
    
 result = (float(counter)/len(ip_lines))*100 #percentage of plagiarism
 
 return result
 



if __name__ == '__main__':
 app.run(debug=True)
