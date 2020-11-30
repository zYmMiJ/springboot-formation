INSERT INTO public.department
(department_id, description, "name")
VALUES(1, 'Produit de luxe', 'District 1');
INSERT INTO public.department
(department_id, description, "name")
VALUES(2, 'construction, armement, force d el''ordre', 'District 2');
INSERT INTO public.department
(department_id, description, "name")
VALUES(3, 'electronique', 'District 3');
INSERT INTO public.department
(department_id, description, "name")
VALUES(4, 'pêche', 'District 4');
INSERT INTO public.department
(department_id, description, "name")
VALUES(5, 'énergie', 'District 5');
INSERT INTO public.department
(department_id, description, "name")
VALUES(6, 'transports', 'District 6');
INSERT INTO public.department
(department_id, description, "name")
VALUES(7, 'bois', 'District 7');
INSERT INTO public.department
(department_id, description, "name")
VALUES(8, 'textile', 'District 8');
INSERT INTO public.department
(department_id, description, "name")
VALUES(9, 'blé', 'District 9');
INSERT INTO public.department
(department_id, description, "name")
VALUES(10, 'bétail', 'District 10');
INSERT INTO public.department
(department_id, description, "name")
VALUES(11, 'agriculture, notamment fruits et légumes', 'District 11');
INSERT INTO public.department
(department_id, description, "name")
VALUES(12, 'charbon', 'District 12');
INSERT INTO public.department
(department_id, description, "name")
VALUES(13, '(graphite, armement, missiles nucléaires', 'District 13');

INSERT INTO public.category
(category_id, department_id, description, "name")
VALUES(1, 1, 'District 1-5', 'Zone 1 (1-5)');
INSERT INTO public.category
(category_id, department_id, description, "name")
VALUES(4, 2, 'District 1-5', 'Zone 1 (1-5)');
INSERT INTO public.category
(category_id, department_id, description, "name")
VALUES(5, 3, 'District 1-5', 'Zone 1 (1-5)');
INSERT INTO public.category
(category_id, department_id, description, "name")
VALUES(6, 4, 'District 1-5', 'Zone 1 (1-5)');
INSERT INTO public.category
(category_id, department_id, description, "name")
VALUES(7, 5, 'District 1-5', 'Zone 1 (1-5)');
INSERT INTO public.category
(category_id, department_id, description, "name")
VALUES(2, 6, 'District 6-10', 'Zone 2 (6-10)');
INSERT INTO public.category
(category_id, department_id, description, "name")
VALUES(8, 7, 'District 6-10', 'Zone 2 (6-10)');
INSERT INTO public.category
(category_id, department_id, description, "name")
VALUES(9, 8, NULL, 'Zone 2 (6-10)');
INSERT INTO public.category
(category_id, department_id, description, "name")
VALUES(10, 9, 'District 6-10', 'Zone 2 (6-10)');
INSERT INTO public.category
(category_id, department_id, description, "name")
VALUES(11, 10, 'District 6-10', 'Zone 2 (6-10)');
INSERT INTO public.category
(category_id, department_id, description, "name")
VALUES(3, 11, 'District 11-13', 'Zone 3 (11-13)');
INSERT INTO public.category
(category_id, department_id, description, "name")
VALUES(12, 12, 'District 11-13', 'Zone 3 (11-13)');
INSERT INTO public.category
(category_id, department_id, description, "name")
VALUES(13, 13, 'District 11-13', 'Zone 3 (11-13)');

