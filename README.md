# Управление на гаранционна поддръжка

Функционалността позволява артикулите, чийто гаранционно поле съдържа стойност различна от нула и отговаря на критериите за връщане или подмяна, да могат да бъдат предложени за рекламация. При подаване на такава заявка се прави проверка за продължителността на периода между датата на закупуване и датата на заявката за връщане или замяна на продукта. Ако гаранционният срок не е изтекъл, потребителя има възможност да замени или върне закупеното. Направеният избор се отразява в status поле в таблицата order_items(ZooStorage), като възможни стойности са 
## Последователност на алгоритъма за работа:
1) Клас Item съдържа поле warrantyPeriod със стойност между 0 и 24, обозначаваща месеците, където 0 индикира липса на гаранционна карта, а 24 - двугодишна гаранция
2) По въведен идентификационен номер на продукт се прави първична проверка за наличие на гаранционна карта(не се продължава към останалата част от алгоритъма при липса на такава)
3) В WarrantyCardValidationOperationProcessor(ZooStore) се валидира причината за необходимостта от рекламация
4) При одобрение на горепосочения критерий, се прави проверка на разликата между текущата дата и датата на покупката
5) Рекламацията бива одобрена при стойност на разликата между датите по-малка от периода на гаранционната карта
6) Финалният етап от процеса се състои в извеждане на остатъка от продължителността на гаранцията и корекция на количеството на продукта в складовата таблица(при необходимост).
