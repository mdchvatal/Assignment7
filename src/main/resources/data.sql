INSERT INTO merit_bank_user ( username, password, role) VALUES ('admin', 'admin', 'ROLE_ADMIN');
INSERT INTO account_holder (combined_balance, first_name, last_name, middle_name, ssn, user_id) VALUES ('1000', 'admin', 'mcadmin', 'd', '123456789', '1');
INSERT INTO checking_account (account_open_date, balance, interest_rate, account_holder_id) VALUES (now(), '1000', '0.001', '1');
