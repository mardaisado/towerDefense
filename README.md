# Tower Defense

## ENNEMI :

  Une classe d'ennemi avec des attributs de PV, Vitesse, apparenece ET Position
  Fonctions communes à tous les ennemis

## DEFENSE :

  Une classe de defenses (Parent, interface?)
    Une sous-classe pour le type de defense avec chaque défense qui a sa sous-classe

  Une classe projectile
    Une sous-classe pour le type de projectile et 1 projectile = 1 sous-classe

  Fonctions communes à toutes les défenses

### Types de Projectiles :

  Missile : Dégats de zone => focus d'une tourelle puis explosion.

  Projectile : Balle touche 1 seul ennemi et disparaît.

  RangeProjectile : Balle qui traverse l'ennemi visé et qui peut toucher plusieurs ennemis (uniquement dans une range).


## DRAW DEFENSE | ENNEMI :

  Fonction draw avec obj draw (Deja tout fait)
  Une liste de choses à afficher

## UPADTE PROJECTILES | ENNEMI :

   Fonction update qui va s'activer chaque x temps et qui va update la position de projectile et ennemis


## MAP :

   Chaque case a ses attributs


## UI :

   Test de drag avec g2xd

   TO DO
