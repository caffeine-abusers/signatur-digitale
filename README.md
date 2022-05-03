Návrh vlastní implementace digitálního podpisu

Navrhni vlastní implementaci digitálního podpisu textových dokumentů s využitím hashovací funkce (např. MD5) a synchronního šifrování (dle vlastního výběru). Údaje o certifikátu a klíč pro šifrování a dešifrování budou evidovány v databázi. Součástí realizace budou tyto dílčí části:

## 1. Návrh a realizace databáze certifikátů pro ověření věrohodnosti digitálního podpisu

- návrh – E-R diagram

- SQL Creation Script

- vytvoření databáze

## Vytvoření rozhraní pro tvorbu certifikátů v libovolném programovacím jazyce s terminálovým I/O, vlastním GUI, nebo přes webové rozhraní

- vytvoření subjektu

-  vytvoření certifikátu subjektu s vygenerovaným šifrovacím klíčem

-  zobrazení ID certifikátu a šifrovacího klíče (údajů o subjektu a certifikátu)

## Vytvoření nástroje pro digitální podepisování textových dokumentů v libovolném programovacím jazyce s terminálovým I/O, vlastním GUI, nebo přes webové rozhraní

- načtení textového souboru

- hash obsahu textového souboru

- zadání ID certifikátu

- zadání šifrovacího klíče

- zašifrování hashe pomocí šifrovacího klíče

- připojení ID certifikátu a zašifrované hashe na konec textového souboru

## Vytvoření nástroje pro ověření věrohodnosti digitálního podpisu v libovolném programovacím jazyce s terminálovým I/O, vlastním GUI, nebo přes webové rozhraní

- načtení textového souboru

- oddělení obsahu, ID certifikátu a zašifrované hashe

- ověření platnosti certifikátu

- dešifrování hashe pomocí ID certifikátu a odpovídajícího šifrovacího klíče z DB

- vygenerování srovnávací hashe odděleného obsahu

- porovnání obou hash hodnot

- vyhodnocení věrohodnosti digitální podpisu
