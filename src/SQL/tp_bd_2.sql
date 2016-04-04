-- SELECT cours.*, salles.adresse, horaires.jour, horaires.heure, horaires.heure+2 AS finCours FROM cours, salles, horaires WHERE salles.NumSalle = cours.NumSalle AND horaires.NomCours = cours.NomCours AND horaires.jour = 'Mardi'

-- SELECT notes.cours, AVG(notes.valeur), MAX(notes.valeur) FROM notes WHERE notes.cours = 'analyse 1';

-- SELECT notes.cours, COUNT(*) AS nbEtudiants FROM notes WHERE notes.valeur IS NULL AND notes.cours = 'informatique';

-- SELECT n1.NumEtudiant, AVG(n1.valeur) AS moyenne FROM notes n1 WHERE n1.valeur IS NOT NULL GROUP BY n1.NumEtudiant;

-- SELECT notes.cours, MIN(notes.valeur) AS noteMin FROM notes WHERE notes.valeur IS NOT NULL GROUP BY notes.cours;

-- SELECT notes.NumEtudiant, AVG(notes.valeur) AS moyenne/*, COUNT(*) AS MatieresValides*/ FROM notes WHERE notes.valeur IS NOT NULL GROUP BY notes.NumEtudiant HAVING COUNT(*) /* Matieres */ > 2;

-- SELECT etudiants.Prenom, etudiants.Nom, COUNT(notes.NumEtudiant) AS nbNotes, MAX(notes.valeur) FROM etudiants, notes WHERE notes.NumEtudiant = etudiants.Numero AND notes.valeur > 14 GROUP BY notes.NumEtudiant HAVING nbNotes > 1;

-- SELECT h1.NomCours AS Cours1, h2.NomCours AS Cours2 FROM horaires h1, horaires h2 WHERE h1.NomCours < h2.NomCours AND h1.jour = h2.jour AND h1.heure = h2.heure ORDER BY h1.NomCours ASC; -- Intéresssant: < supprime les doublons alors que <> les conserves.alter

-- SELECT etudiants.* FROM etudiants, notes WHERE notes.NumEtudiant <> etudiants.Numero GROUP BY notes.NumEtudiant; -- FAUX !!

-- SELECT etudiants.* FROM etudiants WHERE etudiants.Numero NOT IN (SELECT notes.NumEtudiant FROM notes);

-- SELECT etudiants.* FROM etudiants WHERE etudiants.Numero NOT IN (SELECT notes.NumEtudiant FROM notes WHERE notes.valeur IS NOT NULL);

-- SELECT etudiants.Prenom, etudiants.Nom, notes.valeur FROM etudiants, notes WHERE notes.NumEtudiant = etudiants.Numero AND notes.cours = 'informatique' AND notes.valeur > (SELECT AVG(notes.valeur) FROM notes WHERE notes.cours = 'informatique');

SELECT etudiants.Prenom, etudiants.Nom, notes.valeur FROM etudiants, notes WHERE notes.NumEtudiant = etudiants.Numero AND notes.cours = 'analyse 1' ORDER BY notes.valeur DESC, etudiants.Nom ASC, etudiants.Prenom ASC LIMIT 3;