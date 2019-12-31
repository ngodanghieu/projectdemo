CRUD Exercise
Cài đặt:

Import project => Run project(Run VinhomeApplication)
=> Open web browser: http://localhost:8090/swagger-ui.htm  - sẽ hiện các http methods để test(trong taitaocontroller)
Mô tả:
Project mô phỏng các chức năng Create, Read, Update, và Delete.
Project gồm có file 	Swagger2Config: config swagger
			BaiTapController: Điều hướng
			User : Entity tương ứng với bảng user trong database
			UserReponse: Dùng để trả về các trường được phép hiển thị cho người dùng
			UserRequest : Validate cơ bản các trường được nhập vào từ người dùng
			IUserRepository: Repository
			UserService: Các hàm xử lí logic (getAll, search, deleteById, update,findById,save)