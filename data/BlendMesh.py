import bpy
import os

file = '/home/x/workspaceJava/GeoComp/data/exportPoints.txt'
open (file, 'r')

with open (file, 'r') as lines:
    for line in lines:
        print (line)




with open(file 'r') as csvfile:
    ofile = csv.reader(csvfile, delimiter=',')
    next(ofile)
    print (ofile)



# bpy.ops.mesh.primitive_plane_add(radius=1, view_align=False, enter_editmode=False, location=(-1.06609, -1.7686, 1.93891), layers=(True, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False))





ok!!! 

import bpy
import os
import csv

file = '/home/x/workspaceJava/GeoComp/data/exportPoints.csv'

with open(file) as csvfile:
    reader = csv.reader(csvfile)
    
    for i, row in enumerate(reader):
        if i == 0: continue # Skip column titles
        if row : print (float(row[0]))


####################################################
import bpy

# initialize vertices, adn face or edges
vert = [(0, 0, 0), (5, 0, 0), (3, 3, 0)]

# add face, enter vertices indices
# counterclock normal up
face = [(0, 1, 2)]

# create mesh and object data
my_mesh = bpy.data.meshes.new("Triangle")
my_obj = bpy.data.objects.new("Triangle", my_mesh)

# set object location
# my_obj.location = bpy.context.scene.cursor_location
my_obj.location = (0, 0, 0)

# link my_obj to the scene
bpy.context.scene.objects.link(my_obj)

# create object using blender function
my_mesh.from_pydata(vert, [], face)
my_mesh.update(calc_edges = True)



#############################################################

# You can use the bmesh module to do this. You start with creating a new, empty bmesh object with bm = bmesh.new(), then every time you want to add a mesh to this bmesh, you can use bm.from_mesh( mesh ):

import bpy, bmesh

# Reference to existing meshes, replace these with your code generated meshes
m1 = bpy.data.meshes[0] 
m2 = bpy.data.meshes[1]

bm = bmesh.new()

bm.from_mesh( m1 ) # Add mesh 1 to bmesh
bm.from_mesh( m2 ) # Add mesh 2 to bmesh

m3 = bpy.data.meshes.new( "newMesh" )
bm.to_mesh( m3 )

o = bpy.data.objects.new( "new", m3 )
bpy.context.scene.objects.link( o )








################################################################

import bpy

# initialize vertices, adn face or edges
vert1 = [(0, 0, 0), (5, 0, 0), (3, 3, 0)]
vert2 = [(5, 0, 0), (8, 4, 0), (3, 3, 0)]

# add face, enter vertices indices
# counterclock normal up
face1 = [(0, 1, 2)]
face2 = [(0, 1, 2)]

# create mesh and object data
my_mesh1 = bpy.data.meshes.new("Triangle1")
my_obj1 = bpy.data.objects.new("Triangle1", my_mesh1)

my_mesh2 = bpy.data.meshes.new("Triangle2")
my_obj2 = bpy.data.objects.new("Triangle2", my_mesh2)

# set object location
# my_obj.location = bpy.context.scene.cursor_location
my_obj1.location = (0, 0, 0)
my_obj2.location = (0, 0, 0)

# link my_obj to the scene
bpy.context.scene.objects.link(my_obj1)
bpy.context.scene.objects.link(my_obj2)

# create object using blender function
my_mesh1.from_pydata(vert1, [], face1)
my_mesh1.update(calc_edges = True)

my_mesh2.from_pydata(vert2, [], face2)
my_mesh2.update(calc_edges = True)






##########################################################







# import os
# import csv
# from bpy.types import Operator
# from bpy_extras.object_utils import AddObjectHelper, object_data_add
# from mathutils import Vector

# verts = [Vector((0, 0, 0)),
#          Vector((1, 1, 0)),
#          Vector((2, 0, 0)),
#         ]
# edges = []
# faces = [[0, 1, 3]]

# mesh = bpy.data.meshes.new(name="My Triangle")
# mesh.from_pydata(verts, edges, faces)
# mesh.validate(verbose=True)

# object_data_add(context, mesh, operator=self)
