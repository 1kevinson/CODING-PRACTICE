# WealHome SAAS!

L'application pour les syndics de copropriété qui gère les appels de fonds.

Chaque copropriétaire d'une même copropriété doit payer un appel de fonds chaque trimestre, de sorte à assurer le financement de la vie de la copro.

Le montant d'un appel de fonds est donc le quart du montant annuel prévu par la copropriété.

**Exemple** : Exercice (annuel) avec un montant 10.000 € pour la copropriété "Le Greco".

T1 = 2.500 € (répartis sur l'ensemble des copropriétaires)

T2 = 2.500 €

T3 = 2.500 €

T4 = 2.500 €

Pour chacun de ces appels, un copropriétaire paie le montant correspondant à (2.500€ * montant de ses tantièmes / tantièmes de la copropriété).

Exemple s'il lui est attribué 500 tantièmes sur 1000 de la copro, alors il devra payer 2.500 * 500 / 1000 = 1.250 €


# User Story 1 - Lancer un appel de fonds

En tant que **Syndic**,
Je souhaite **lancer l'appel de fonds** du trimestre courant
De sorte à faire **payer** les copropriétaires leur sommes dues.

## Critères d'acceptation

- Chaque appel de fonds doit être labelisé TX désigne le n° d'appel (T1, T2 ..., T4).


-  Un appel de fonds est lancé lorsque le trimestre est entamé.


-  Un appel de fonds n'est lancé qu'une seule fois par trimestre.


-  Le montant d'un appel de fonds est égal au quart de celui du budget de l'exercice.


-  Un appel de fonds ne peut être lancé que si la répartition des tantièmes des copropriétaires  est en phase avec les tantièmes de copropriété.


-  Chaque copropriétaire doit se voir exiger la somme qui correspond à ses tantièmes.


- Si un copropriétaire n'a pas payé le précédent appel de fonds, alors la somme non payée se cumule à l'appel de fonds courant.


## Atelier BDD - Challenge du besoin

- "TX désigne le n° d'appel "

  => Bruno, Syndic, vient de prendre l'appli et lance son premier appel de fonds. On est en avril et l'exercice commence en janvier.
  C'est donc T1 ou T2 ?
  D'ailleurs, un exercice commence-t-il toujours en janvier ?


-  "lorsque le trimestre entamé"

   => y'a-t-il une règle dans les règlements qui précisent un jour particulier, ou n'importe quel jour dans le trimestre est valable ?


-  "Un appel de fonds n'est lancé qu'une seule fois par trimestre."

   => Que faire s'il y a eu une erreur de saisie tel un tantième inversé avec celui d'un autre copropriétaire et que l'appel a été lancé ?


- "au quart de celui du budget de l'exercice"
  => le budget de cet exercice est-il changeant ou fixe d'années en années ?


- " est en phase avec les tantièmes de copropriété"

  => Cela signifie "égale" ou "n'excédant pas" ?


- "Chaque copropriétaire doit se voir exiger la somme qui correspond à ses tantièmes"

  => Par quel biais ?
  Notification dans l'application, e-mail, avec pièces jointes ou non ?

  Immédiatement après avoir lancé l'appel ? Ou bien quelque temps après comme à une heure bien précise ?



