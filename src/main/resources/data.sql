INSERT INTO account (id, account_Number, account_Name, account_Type, balance_Date, currency, opening_Available_Balance, user_id) VALUES (1, '123485', 'ATO', 'SAVINGS', '2019-02-10', 'AUD', '14234.55', 1);
INSERT INTO account (id, account_Number, account_Name, account_Type, balance_Date, currency, opening_Available_Balance, user_id) VALUES (2, '453485', 'ATO', 'SAVINGS', '2019-02-11', 'SGD', '14234.55', 1);
INSERT INTO account (id, account_Number, account_Name, account_Type, balance_Date, currency, opening_Available_Balance, user_id) VALUES (3, '555485', 'ATO', 'SAVINGS', '2019-02-12', 'AUD', '14234.55', 2);

insert into account_transaction (id, amount, debit_credit, transaction_narrative, value_date, account_id) values (1, '234234.11', 'DEBIT', '', '2019-01-31', 1);
insert into account_transaction (id, amount, debit_credit, transaction_narrative, value_date, account_id) values (2, '114.11', 'DEBIT', '', '2019-02-03', 2);
insert into account_transaction (id, amount, debit_credit, transaction_narrative, value_date, account_id) values (3, '2.45', 'DEBIT', '', '2019-05-31', 1);