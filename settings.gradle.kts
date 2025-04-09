rootProject.name = "target-demo"
include("src:integrationTest:untitled")
findProject(":src:integrationTest:untitled")?.name = "untitled"
