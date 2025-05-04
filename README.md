# Simulateur d'Impôt sur le Revenu

## Description

Ce projet permet de simuler le calcul de l'impôt sur le revenu en fonction de la situation familiale et des revenus du foyer. Il prend en compte plusieurs éléments fiscaux, tels que les tranches d'imposition, la décote, la contribution exceptionnelle et les parts fiscales, pour fournir une estimation précise de l'impôt net à payer.

Le code est structuré de manière modulaire, en séparant les différentes étapes du calcul dans des classes utilitaires distinctes pour une maintenance simplifiée.

---

## Fonctionnalités

- **Calcul de l'impôt brut** : Calcul de l'impôt brut basé sur les tranches d'imposition progressives.
- **Décote** : Application d'une décote si l'impôt brut est inférieur à un seuil spécifique.
- **Contribution exceptionnelle** : Calcul d'une contribution exceptionnelle pour les foyers ayant des revenus élevés.
- **Parts fiscales** : Calcul du nombre de parts fiscales en fonction de la situation familiale et du nombre d'enfants à charge.
- **Validation des données** : Vérification de la validité des données fournies (revenus, enfants, etc.).

---

## Structure du Projet

Le projet est composé des éléments suivants :

### 1. **Simulateur**

La classe principale qui orchestre le calcul de l'impôt. Elle utilise différentes classes utilitaires pour :
- Calculer l'abattement fiscal et le revenu fiscal de référence.
- Déterminer le nombre de parts fiscales.
- Calculer l'impôt brut avant décote.
- Appliquer la décote si nécessaire.
- Calculer la contribution exceptionnelle.
- Calculer l'impôt net à payer après décote.

### 2. **TaxCalculator**

Cette classe calcule l'impôt brut avant décote en fonction des tranches d'imposition et des taux applicables aux différentes tranches.

### 3. **AbattementCalculator**

Calcule l'abattement fiscal qui est appliqué au revenu net pour déterminer le revenu fiscal de référence.

### 4. **ContributionCalculator**

Calcule la contribution exceptionnelle pour les foyers ayant des revenus élevés.

### 5. **DecoteCalculator**

Calcule la décote applicable à l'impôt brut, en fonction des seuils définis pour les célibataires, les couples, etc.

### 6. **PartCalculator**

Calcule le nombre de parts fiscales en fonction de la situation familiale (célibataire, marié, pacsé, etc.) et du nombre d'enfants à charge, ainsi que d'autres facteurs comme les enfants handicapés ou un parent isolé.

### 7. **SituationFamiliale**

Une énumération représentant les différentes situations familiales possibles (célibataire, marié, pacsé, divorcé, veuf).