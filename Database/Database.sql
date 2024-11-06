drop DATABASE SoftSkillsOnlineLearningSystem;

-- Create the SoftSkillsOnlineLearningSystem database
CREATE DATABASE SoftSkillsOnlineLearningSystem;
GO
USE SoftSkillsOnlineLearningSystem;
GO

-- Table: users
CREATE TABLE users (
    id INT IDENTITY(1,1) PRIMARY KEY,          
    full_name VARCHAR(255) NOT NULL,           
    gender NVARCHAR(10) DEFAULT 'Other',       
    email VARCHAR(255) UNIQUE NOT NULL,        
    password VARCHAR(255) NOT NULL,
    role NVARCHAR(50) DEFAULT 'Student',         
	address VARCHAR(255),
	hash VARCHAR(255),
	status bit DEFAULT 1
);

CREATE TABLE user_media (
    id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT NOT NULL,
    media_type NVARCHAR(50) NOT NULL,
    media_data VARBINARY(MAX) NOT NULL,
    note NVARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Table: user_contacts
CREATE TABLE user_contacts (
    id INT IDENTITY(1,1) PRIMARY KEY,           
    user_id INT NOT NULL,                       
    contact_type NVARCHAR(50) NOT NULL,
    contact_value VARCHAR(255) NOT NULL,        
    is_preferred bit DEFAULT 0,
    CONSTRAINT FK_UserContacts_Users FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Table: courses
CREATE TABLE courses (
    id INT IDENTITY(1,1) PRIMARY KEY,           
    title VARCHAR(255) NOT NULL,                -- Tên khóa học
    tag_line VARCHAR(255),                      -- Tiêu đề phụ
    description TEXT,                           -- Mô tả khóa học
    category VARCHAR(255),                      -- Danh mục khóa học
    basic_package_price DECIMAL(10, 2),         -- Giá của gói cơ bản
    advanced_package_price DECIMAL(10, 2),      -- Giá của gói nâng cao
    owner_id INT,                               -- ID của người sở hữu khóa học
    status NVARCHAR(50) DEFAULT 'Draft',        -- Trạng thái khóa học (mặc định là 'Draft')
    is_sponsored BIT,                           -- Khóa học có được tài trợ hay không
    created_at DATETIME DEFAULT GETDATE(),      -- Ngày tạo khóa học
    updated_at DATETIME DEFAULT GETDATE(),      -- Ngày cập nhật khóa học
    CONSTRAINT FK_Courses_Users FOREIGN KEY (owner_id) 
        REFERENCES users(id) ON DELETE SET NULL  -- Khóa ngoại tham chiếu đến bảng users
);

CREATE TABLE course_content (
    id INT PRIMARY KEY,
    course_id INT FOREIGN KEY REFERENCES courses(id),
    content NVARCHAR(MAX),
    created_at DATETIME,
    updated_at DATETIME
);
 
-- Table: course_media
CREATE TABLE course_media (
    [id] INT IDENTITY(1,1) PRIMARY KEY,
    [course_id] INT NOT NULL,
    [media_type] VARCHAR(10) NOT NULL, -- 'image' hoặc 'video' 
    [file_name] NVARCHAR(255), -- tên file với extension
    [title] NVARCHAR(255), -- tiêu đề của media (optional)
    [display_order] INT, -- thứ tự hiển thị
    [created_at] DATETIME DEFAULT GETDATE(),
    [updated_at] DATETIME,
    FOREIGN KEY (course_id) REFERENCES courses(id)
)

-- Table: subjects
CREATE TABLE subjects (
    id INT IDENTITY(1,1) PRIMARY KEY,          -- Unique identifier for each subject
    name NVARCHAR(255) NOT NULL,               -- Name of the subject
    category NVARCHAR(255),                     -- Category of the subject
    owner_id INT,                               -- ID of the user who owns the subject
    status NVARCHAR(50) DEFAULT 'Draft',       -- Status of the subject (e.g., Draft, Published)
    created_at DATETIME DEFAULT GETDATE(),      -- Creation timestamp
    updated_at DATETIME DEFAULT GETDATE(),      -- Last update timestamp
    CONSTRAINT FK_Subjects_Users FOREIGN KEY (owner_id) REFERENCES users(id) ON DELETE SET NULL
);

-- Table: packages
CREATE TABLE packages (
    id INT IDENTITY(1,1) PRIMARY KEY,
    course_id INT NOT NULL,
    package_name NVARCHAR(255),
    price DECIMAL(10, 2),
    sale_price DECIMAL(10, 2),
    sale_start DATETIME DEFAULT GETDATE(),
    sale_end DATETIME DEFAULT DATEADD(DAY, 7, GETDATE()),
    access_duration INT NOT NULL,
    CONSTRAINT FK_CourseSale_Courses FOREIGN KEY (course_id) 
        REFERENCES courses(id) ON DELETE CASCADE
);

-- Table: thumbnails
CREATE TABLE Thumbnails (
    id INT PRIMARY KEY IDENTITY(1,1),   -- Auto-incrementing primary key
    courseId INT NOT NULL,              -- Foreign key reference to Courses table
    fileName NVARCHAR(255),             -- File name of the uploaded image
    fileData VARBINARY(MAX) NOT NULL,   -- Binary data for the image file
    uploaded_at DATETIME DEFAULT GETDATE(), -- Timestamp for when the image was uploaded

    -- Foreign key constraint to ensure thumbnails are linked to existing courses
    FOREIGN KEY (courseId) REFERENCES Courses(id)
        ON DELETE CASCADE                -- Delete associated thumbnails if the course is deleted
);

-- Table: course_details
CREATE TABLE course_details (
    course_id INT PRIMARY KEY,              
    section_title VARCHAR(255),               
    content TEXT,                                          
    created_at DATETIME DEFAULT GETDATE(),    
    updated_at DATETIME DEFAULT GETDATE(),    
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE 
);
DROP TABLE [SoftSkillsOnlineLearningSystem].[dbo].[course_details];

-- Table: course_translations
CREATE TABLE course_translations (
    id INT IDENTITY(1,1) PRIMARY KEY,           
    course_id INT,                              
    language_code VARCHAR(10) NOT NULL,         
    title VARCHAR(255),                         
    description TEXT,                           
    CONSTRAINT FK_CourseTranslations_Courses FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

-- Table: registrations
CREATE TABLE registrations (
    id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT NOT NULL,
    package_id INT NOT NULL,
    course_id INT NOT NULL,
    total_cost DECIMAL(10, 2),
    status NVARCHAR(50) DEFAULT 'Submitted',
    valid_from DATETIME DEFAULT GETDATE(),
    valid_to DATETIME DEFAULT DATEADD(WEEK, 1, GETDATE()), -- Current time +1 week
    created_by INT,
    notes NVARCHAR(255),
    CONSTRAINT FK_Registrations_User FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE NO ACTION,
    CONSTRAINT FK_Registrations_Package FOREIGN KEY (package_id) REFERENCES packages(id) ON DELETE NO ACTION,
    CONSTRAINT FK_Registrations_Course FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE NO ACTION,
    CONSTRAINT FK_Registrations_CreatedBy FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE NO ACTION
);

-- Table: registration_media
CREATE TABLE registration_media (
    id INT IDENTITY(1,1) PRIMARY KEY,
    registration_id INT NOT NULL,
    media_type NVARCHAR(50) NOT NULL,
    media_data VARBINARY(MAX) NOT NULL,
    note NVARCHAR(255),
    FOREIGN KEY (registration_id) REFERENCES registrations(id)
);

-- Table: user_courses
CREATE TABLE user_courses (
    id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT NOT NULL,
    course_id INT NOT NULL,
    access_start DATETIME DEFAULT GETDATE(),
    access_end DATETIME DEFAULT DATEADD(DAY, 7, GETDATE()),
    CONSTRAINT FK_UserCourses_Users FOREIGN KEY (user_id) 
        REFERENCES users(id) ON DELETE NO ACTION,
    CONSTRAINT FK_UserCourses_Courses FOREIGN KEY (course_id) 
        REFERENCES courses(id) ON DELETE NO ACTION,
);

-- Table: questions
CREATE TABLE questions (
    id INT IDENTITY(1,1) PRIMARY KEY,           
    course_id INT,                              
    content NVARCHAR(MAX) NOT NULL,             
    media_url VARCHAR(255),                     
    level NVARCHAR(50) DEFAULT 'Medium',        
    created_at DATETIME DEFAULT GETDATE(),      
    updated_at DATETIME DEFAULT GETDATE(),      
    CONSTRAINT FK_Questions_Courses FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

-- Table: answer_options
CREATE TABLE answer_options (
    id INT IDENTITY(1,1) PRIMARY KEY,           
    question_id INT NOT NULL,                   
    option_text NVARCHAR(MAX) NOT NULL,         
    is_correct BIT DEFAULT 0,                   
    CONSTRAINT FK_AnswerOptions_Questions FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
);

-- Table: quizzes
CREATE TABLE quizzes (
    id INT IDENTITY(1,1) PRIMARY KEY,           
    course_id INT,                              
    title VARCHAR(255) NOT NULL,                
    duration INT,                               
    pass_rate DECIMAL(5, 2),                    
    created_at DATETIME DEFAULT GETDATE(),      
    updated_at DATETIME DEFAULT GETDATE(),      
    CONSTRAINT FK_Quizzes_Courses FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

-- Table: quiz_questions
CREATE TABLE quiz_questions (
    quiz_id INT,                                
    question_id INT,                            
    PRIMARY KEY (quiz_id, question_id),         
    CONSTRAINT FK_QuizQuestions_Quizzes FOREIGN KEY (quiz_id) REFERENCES quizzes(id) ON DELETE CASCADE,
    CONSTRAINT FK_QuizQuestions_Questions FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE NO ACTION
);

CREATE TABLE categories (
    id INT IDENTITY(1,1) PRIMARY KEY, 
    name NVARCHAR(255) NOT NULL
);

-- Table: blog_posts
CREATE TABLE blog_posts (
    id INT IDENTITY(1,1) PRIMARY KEY,
    title NVARCHAR(255),
    thumbnail_url NVARCHAR(255),
    category_id INT,
    content NVARCHAR(MAX),
    author_id INT,
    status NVARCHAR(50) DEFAULT 'Draft',
    created_at DATETIME DEFAULT GETDATE(),
    updated_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (category_id) REFERENCES categories(id),  -- Ensure the categories table exists
    FOREIGN KEY (author_id) REFERENCES users(id)
);

-- Table: tags
CREATE TABLE tags (
    id INT IDENTITY(1,1) PRIMARY KEY,           
    name VARCHAR(255) NOT NULL                  
);

-- Table: blog_post_tags
CREATE TABLE blog_post_tags (
    blog_post_id INT,                           
    tag_id INT,                                 
    PRIMARY KEY (blog_post_id, tag_id),         
    CONSTRAINT FK_BlogPostTags_BlogPosts FOREIGN KEY (blog_post_id) REFERENCES blog_posts(id) ON DELETE CASCADE,
    CONSTRAINT FK_BlogPostTags_Tags FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE
);

-- Table: sliders
CREATE TABLE sliders (
    id INT IDENTITY(1,1) PRIMARY KEY,           
    title VARCHAR(255),                         
    image_url VARCHAR(255),                     
    backlink VARCHAR(255),                      
    status NVARCHAR(50) DEFAULT 'Visible',      
    created_at DATETIME DEFAULT GETDATE(),      
    updated_at DATETIME DEFAULT GETDATE()       
);

-- Table: settings
CREATE TABLE settings (
    id INT IDENTITY(1,1) PRIMARY KEY,           
    setting_type VARCHAR(255),                  
    value VARCHAR(255),                         
    order_num INT,                              
    status NVARCHAR(50) DEFAULT 'Active'        
);

-- Indexes for optimized querying
CREATE INDEX idx_email ON users(email);         
CREATE INDEX idx_course_title ON courses(title); 
CREATE INDEX idx_registration_status ON registrations(status);
CREATE INDEX idx_blog_category ON blog_posts(category_id); 

