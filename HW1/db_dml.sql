DELETE FROM trip;

-- Trips from Porto to Lisboa
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('PO-LI-001', 'Porto', 'Lisboa', '08:00:00', 20);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('PO-LI-002', 'Porto', 'Lisboa', '10:00:00', 20);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('PO-LI-003', 'Porto', 'Lisboa', '12:00:00', 20);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('PO-LI-004', 'Porto', 'Lisboa', '16:00:00', 20);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('PO-LI-005', 'Porto', 'Lisboa', '20:00:00', 20);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('PO-LI-006', 'Porto', 'Lisboa', '23:00:00', 20);

-- Trips from Lisboa to Porto
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('LI-PO-001', 'Lisboa', 'Porto', '08:00:00', 20);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('LI-PO-002', 'Lisboa', 'Porto', '11:00:00', 22);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('LI-PO-003', 'Lisboa', 'Porto', '14:00:00', 25);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('LI-PO-004', 'Lisboa', 'Porto', '18:00:00', 23);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('LI-PO-005', 'Lisboa', 'Porto', '20:00:00', 20);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('LI-PO-006', 'Lisboa', 'Porto', '22:00:00', 22);

-- Trips from Porto to Braga
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('PO-BR-001', 'Porto', 'Braga', '09:00:00', 15);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('PO-BR-002', 'Porto', 'Braga', '11:00:00', 16);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('PO-BR-003', 'Porto', 'Braga', '13:00:00', 15);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('PO-BR-004', 'Porto', 'Braga', '15:00:00', 17);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('PO-BR-005', 'Porto', 'Braga', '17:00:00', 15);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('PO-BR-006', 'Porto', 'Braga', '19:00:00', 16);

-- Trips from Braga to Porto
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('BR-PO-001', 'Braga', 'Porto', '09:00:00', 15);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('BR-PO-002', 'Braga', 'Porto', '11:00:00', 16);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('BR-PO-003', 'Braga', 'Porto', '13:00:00', 15);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('BR-PO-004', 'Braga', 'Porto', '15:00:00', 17);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('BR-PO-005', 'Braga', 'Porto', '17:00:00', 15);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('BR-PO-006', 'Braga', 'Porto', '19:00:00', 16);

-- Trips from Lisboa to Braga
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('LI-BR-001', 'Lisboa', 'Braga', '08:00:00', 30);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('LI-BR-002', 'Lisboa', 'Braga', '10:00:00', 32);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('LI-BR-003', 'Lisboa', 'Braga', '17:00:00', 35);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('LI-BR-004', 'Lisboa', 'Braga', '19:00:00', 33);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('LI-BR-005', 'Lisboa', 'Braga', '21:00:00', 30);

-- Trips from Braga to Lisboa
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('BR-LI-001', 'Braga', 'Lisboa', '08:00:00', 30);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('BR-LI-002', 'Braga', 'Lisboa', '10:00:00', 32);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('BR-LI-003', 'Braga', 'Lisboa', '17:00:00', 35);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('BR-LI-004', 'Braga', 'Lisboa', '22:00:00', 33);

-- Trips from Porto to Aveiro
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('PO-AV-001', 'Porto', 'Aveiro', '09:00:00', 10);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('PO-AV-002', 'Porto', 'Aveiro', '12:00:00', 12);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('PO-AV-003', 'Porto', 'Aveiro', '15:00:00', 15);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('PO-AV-004', 'Porto', 'Aveiro', '18:00:00', 13);

-- Trips from Aveiro to Porto
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('AV-PO-001', 'Aveiro', 'Porto', '10:00:00', 10);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('AV-PO-002', 'Aveiro', 'Porto', '13:00:00', 12);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('AV-PO-003', 'Aveiro', 'Porto', '16:00:00', 15);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('AV-PO-004', 'Aveiro', 'Porto', '19:00:00', 13);

-- Trips from Lisboa to Aveiro
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('LI-AV-001', 'Lisboa', 'Aveiro', '08:00:00', 25);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('LI-AV-002', 'Lisboa', 'Aveiro', '10:00:00', 27);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('LI-AV-003', 'Lisboa', 'Aveiro', '17:00:00', 30);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('LI-AV-004', 'Lisboa', 'Aveiro', '19:00:00', 28);

-- Trips from Aveiro to Lisboa
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('AV-LI-001', 'Aveiro', 'Lisboa', '08:00:00', 25);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('AV-LI-002', 'Aveiro', 'Lisboa', '10:00:00', 27);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('AV-LI-003', 'Aveiro', 'Lisboa', '17:00:00', 30);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('AV-LI-004', 'Aveiro', 'Lisboa', '22:00:00', 28);

-- Trips from Braga to Aveiro
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('BR-AV-001', 'Braga', 'Aveiro', '09:00:00', 20);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('BR-AV-002', 'Braga', 'Aveiro', '12:00:00', 22);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('BR-AV-003', 'Braga', 'Aveiro', '15:00:00', 25);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('BR-AV-004', 'Braga', 'Aveiro', '18:00:00', 19);

-- Trips from Aveiro to Braga
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('AV-BR-001', 'Aveiro', 'Braga', '10:00:00', 20);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('AV-BR-002', 'Aveiro', 'Braga', '13:00:00', 22);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('AV-BR-003', 'Aveiro', 'Braga', '16:00:00', 25);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('AV-BR-004', 'Aveiro', 'Braga', '19:00:00', 19);

-- Trips from Coimbra to Lisboa
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('CO-LI-001', 'Coimbra', 'Lisboa', '09:00:00', 20);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('CO-LI-002', 'Coimbra', 'Lisboa', '12:00:00', 22);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('CO-LI-003', 'Coimbra', 'Lisboa', '15:00:00', 16);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('CO-LI-004', 'Coimbra', 'Lisboa', '18:00:00', 23);

-- Trips from Lisboa to Coimbra
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('LI-CO-001', 'Lisboa', 'Coimbra', '09:00:00', 20);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('LI-CO-002', 'Lisboa', 'Coimbra', '12:00:00', 22);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('LI-CO-003', 'Lisboa', 'Coimbra', '15:00:00', 16);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('LI-CO-004', 'Lisboa', 'Coimbra', '18:00:00', 23);

-- Trips from Coimbra to Aveiro
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('CO-AV-001', 'Coimbra', 'Aveiro', '09:00:00', 10);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('CO-AV-002', 'Coimbra', 'Aveiro', '12:00:00', 12);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('CO-AV-003', 'Coimbra', 'Aveiro', '15:00:00', 15);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('CO-AV-004', 'Coimbra', 'Aveiro', '18:00:00', 13);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('CO-AV-005', 'Coimbra', 'Aveiro', '21:00:00', 10);

-- Trips from Aveiro to Coimbra
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('AV-CO-001', 'Aveiro', 'Coimbra', '09:00:00', 10);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('AV-CO-002', 'Aveiro', 'Coimbra', '12:00:00', 12);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('AV-CO-003', 'Aveiro', 'Coimbra', '15:00:00', 8);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('AV-CO-004', 'Aveiro', 'Coimbra', '18:00:00', 13);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('AV-CO-005', 'Aveiro', 'Coimbra', '21:00:00', 10);
INSERT INTO trip (trip_code, origin, destination, time, price) VALUES ('AV-CO-006', 'Aveiro', 'Coimbra', '23:00:00', 12);