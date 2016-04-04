USE TPBD;

-- Q1

-- SELECT * FROM etudiants WHERE Ville = 'Grenoble';

-- Q2

-- SELECT * FROM etudiants WHERE Ville = 'Paris' AND Prenom = 'Louis';

-- Q3

-- SELECT * FROM horaires WHERE jour = 'mardi' AND heure = 11;

-- Q4

-- SELECT * FROM horaires WHERE jour = 'lundi' AND heure < 12;

-- Q5

-- SELECT * FROM etudiants WHERE Ville = 'Lyon' OR Ville = 'Paris' ;

-- Q6

-- SELECT DISTINCT Prenom from etudiants;

-- Q7

-- SELECT DISTINCT cours FROM notes WHERE valeur IS NULL ORDER BY COURS ASC;

-- Q8

-- SELECT cours.*, salles.adresse FROM cours, salles WHERE cours.NumSalle = salles.NumSalle AND salles.adresse = 'Ampère nord';

-- SELECT cours.*, salles.adresse FROM cours INNER JOIN salles ON cours.NumSalle = salles.NumSalle WHERE salles.adresse = 'Ampère nord'; -- AUTRE METHODE

-- SELECT * FROM cours WHERE NumSalle = (SELECT NumSalle FROM salles WHERE adresse = 'Ampère nord'); -- AUTRE METHODE

-- Q9

-- SELECT etudiants.* FROM etudiants, notes WHERE etudiants.Numero = notes.NumEtudiant AND notes.valeur IS NULL AND cours = 'Analyse 1';

-- SELECT * FROM etudiants WHERE Numero IN (SELECT NumEtudiant FROM notes WHERE valeur IS NULL AND cours = 'Analyse 1'); -- AUTRE METHODE

-- Q10

-- SELECT horaires.NomCours, horaires.heure, cours.NumSalle, salles.adresse FROM horaires, cours, salles WHERE horaires.NomCours = cours.NomCours AND salles.NumSalle = cours.NumSalle AND horaires.jour = 'Lundi' ORDER BY horaires.heure ASC;

-- Q11

-- SELECT c1.* FROM cours c1, cours c2 WHERE c1.Pre_requis = c2.Pre_requis AND c2.NomCours = 'Analyse 2';

-- SELECT * FROM cours WHERE Pre_requis = (SELECT Pre_requis FROM cours WHERE NomCours = 'Analyse 2'); -- AUTRE METHODE

-- Q12

-- SELECT cours.NomCours, salles.adresse, horaires.heure
-- FROM salles, cours, notes, etudiants, horaires
-- WHERE cours.NumSalle = salles.NumSalle
-- AND cours.NomCours = notes.cours
-- AND notes.valeur IS NULL
-- AND notes.NumEtudiant = etudiants.Numero
-- AND horaires.NomCours = cours.NomCours
-- AND horaires.jour = 'mardi'
-- AND etudiants.Nom = 'DEBUSSY'
-- ORDER BY horaires.heure ASC

-- Q13

-- NB : On ne précise pas le nom des cours, sinon on ne peut plus utiliser le DISCTINCT : les étudiants appraraîtront donc pour chaque cours qu'ils ont validé.

-- SELECT DISTINCT etudiants.Nom, etudiants.Prenom
-- FROM etudiants, notes, cours
-- WHERE
--    notes.cours = cours.NomCours
--     AND cours.Pre_requis IS NULL
--    AND etudiants.Numero = notes.NumEtudiant
--    AND notes.valeur IS NOT NULL
    
-- BONUS TRACK

-- SELECT * FROM cours WHERE cours.NomCours LIKE '%tique%'

-- SELECT * FROM cours WHERE cours.NomCours <> 'Mécanique Générale' AND cours.NumSalle = 2

-- SELECT cours.NomCours, cours.NumSalle, salles.adresse FROM cours, salles WHERE cours.NumSalle = salles.NumSalle AND cours.Pre_requis = 'Analyse 2';

-- SELECT cours.NomCours, horaires.jour, salles.adresse
-- FROM cours, horaires, salles
-- WHERE
-- cours.NumSalle = salles.NumSalle
-- AND horaires.NomCours = cours.NomCours
-- AND horaires.jour = 'lundi'
-- AND salles.adresse = 'Ampère Nord'

-- SELECT DISTINCT salles.adresse FROM salles, cours WHERE salles.NumSalle = cours.NumSalle AND cours.NomCours LIKE '%analyse%'

SELECT etudiants.Nom, etudiants.Prenom, n1.cours as cours, n1.valeur AS noteCours, n2.cours AS coursPreRequis, n2.valeur AS notePreRequis
FROM etudiants, notes n1, notes n2, cours c1
WHERE
    n1.NumEtudiant = etudiants.Numero
    AND n2.NumEtudiant = n1.NumEtudiant
    AND c1.NomCours = n1.cours
    AND (n2.cours = c1.Pre_requis)