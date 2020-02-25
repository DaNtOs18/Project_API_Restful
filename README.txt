db_name: project_api_restful
db_config: application.properties
repository: https://github.com/DaNtOs18/Project_API_Restful

<!-- SERVER -->
zip: Project_API_Restful
jar: project_api_restful

<!-- FRONT-END -->
folder: api

<!-- LOGIN FRONT-END -->
user: user
password: user

<!-- URLS RESTFUL -->
--ITEM:
	-create: post -> /item
	-read all: get -> /items
	-read one: get -> /item/id
	-update: put -> /item/id
	-delete: delete -> /item/id 

--PRICE REDUCTION
	-create: post -> /item/price_reduction
	-update: put -> /item/price_reduction/id
	-delete: delete -> /item/price_reduction/id 

--USER
	-login: post -> /login
	-read one: get -> /user/id
	-update: put -> /user/id
	-delete: delete -> /user/id 

--SUPPLIER
	-create: post -> /supplier
	-read all: get -> /supplier
	-read one: get -> /supplier/id
	-update: put -> /supplier/id
	-delete: delete -> /supplier/id 
