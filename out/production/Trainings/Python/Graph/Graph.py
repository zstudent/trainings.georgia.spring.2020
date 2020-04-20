
#Python Graph class
class Graph:

	#Constructor
	def __init__(self,numVerticies):
		self.numVerticies = numVerticies
		self.adjList = []
		for i in range(0,numVerticies):
			self.adjList.append(list())

	#Add edge
	def addEdge(self,v1,v2):
		self.adjList[v1] += v2
		self.adjList[v2] += v1

	#Number of verticies
	def getNumVerticies(self):
		return self.numVerticies

	#Get neighbours of vertex, in iterator object form.
	def getNeighbours(self,vertex):
		return iter(self.adjList[vertex])


			


