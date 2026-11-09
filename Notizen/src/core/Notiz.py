from datetime import datetime


class Notiz: 

    # Variablen
    Titel  = ""
    timestamp = datetime.now()
    Inhalt = ""

    # Konstruktor
    def __init__(self, titel, inhalt):
        self.Titel = titel
        self.Inhalt = inhalt
        self.timestamp = datetime.now()

    def set_Inhalt(self, inhalt):
        self.Inhalt = inhalt
        self.timestamp = datetime.now()
    

    def set_Titel(self, titel):
        self.Titel = titel
        self.timestamp = datetime.now()
    
    def get_inhalt(self):
        return self.Inhalt
    
    def get_titel(self):
        return self.Titel
    
    def get_Timestamp(self):
        return self.timestamp
    
    
