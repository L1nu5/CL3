A. Frontend tasks-
	1. Create a directory named Plagiarism & copy all the files into it.
	2. Install- 
		a. Bottle (pip install bottle)
		b. PyMongo (pip install pymongo)
	3. Run the server-
		python index.py

B. Backend tasks- 
	1. Run mongodb server, mongo from another command line
	2. In mongo client, use following commands in the given sequence-
		a. use plagarize
		b. db.plagarize.insert({"text":"Text_to_use_as_source1"})
			.
			.
		   db.plagarize.insert({"text":"Text_to_use_as_sourceN"})
		c. db.plagarize.find().pretty()

C. To Run on Smartphone, 
	1. Check the IP address of the server using 'ifconfig'.
    2. Open the browser, use URL- ipaddress:port_no/
    3. To upload a text file from your phone, copy a text file from PC to your phone's memory.