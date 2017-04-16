#coded by Ameeth
import os
import unittest
def read(filename):
	a=open(filename,'r')
	inx=[]
	for inp in a:
		inx.append(int(inp))
	return (inx)

def sort(inp):
	inp.sort()
	return inp

def search(key,lst,first,last):
	if(first<=last):	
		mid=int((last+first)/2)
		if(key==lst[mid]):
			return mid
		elif(key < lst[mid]):
			return search(key,lst,first,mid-1)
		elif(key > lst[mid]):
			return search(key,lst,mid+1,last)
		
class Test(unittest.TestCase):
	def test_postive(self):
		print("In positive")
		self.assertEqual(search(1,[0,1,2,3,4,5],0,5),1)
	def test_negative(self):
		print("In negative")
		self.assertEqual(search(11,[0,1,2,3,4,5],0,5),None)
	
some=read("input.txt")
print("Input Array is :",some)
srt=sort(some)
print("Sorted Array is :",srt)
x=int(input("\nEnter the key to be searched\t"))
ind=search(x,srt,0,len(srt)-1)
print("Value found at index :",ind)
print("Unit testing :")
unittest.main()

''' OUTPUT 
[akmyths@localhost A1]$ python A1.py 
Input Array is : [66, 23, 76, 243, 769, 45, 89, 235, 967, 397, 239, 763, 756, 567, 3466, 74577, 2351, 8511]
Sorted Array is : [23, 45, 66, 76, 89, 235, 239, 243, 397, 567, 756, 763, 769, 967, 2351, 3466, 8511, 74577]

Enter the key to be searched	8511
('Value found at index :', 16)
Unit testing :
..
----------------------------------------------------------------------
Ran 2 tests in 0.000s

OK
[akmyths@localhost A1]$ 
'''
