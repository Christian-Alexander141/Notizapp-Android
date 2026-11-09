from Notiz import Notiz

testTitel = input("Titel: ")
testInhalt = input("Inhalt: ")

testNotiz = Notiz(testTitel, testInhalt)

print("Notiz erstellt!")
print("================================")
print("Titel: " + testNotiz.get_titel())
print("Inhalt: " + testNotiz.get_inhalt())
print("Timestamp: " + str(testNotiz.get_Timestamp()))
