alter table roles
       drop index UK_ofx66keruapi6vyqpv6f2or37
GO
    alter table product_categories
       drop constraint FK26v0rnjfnu7lkg47txe1rcem9
GO
    alter table product_categories
       drop constraint FKlda9rad6s180ha3dl1ncsp8n7
GO
    alter table users_roles
       drop constraint FKj6m8fwv7oqv74fcehir1a9ffy
GO
    alter table users_roles
       drop constraint FK2o0jvgh89lemvvo17cbqvdxaa
GO
    drop table category
GO
    drop table product_categories
GO
    drop table products
GO
    drop table roles
GO
    drop table users
GO
    drop table users_roles
GO