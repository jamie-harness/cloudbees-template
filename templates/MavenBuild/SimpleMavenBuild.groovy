class SimpleMavenBuild implements Serializable {
    def steps

    // Constructor
    SimpleMavenBuild(steps) { this.steps = steps }

    // Method to build Maven projects
    def mavenBuild(String buildFile, String buildTarget) {
        steps.echo "Validating Build Parameters"

        if (checkMandatoryFields(buildFile)) {
            def mavenCommand = "mvn -f ${buildFile} ${buildTarget ?: 'install'}"
            steps.sh "cd ${steps.pwd()}; ${mavenCommand}"
        } else {
            steps.error "Build parameter validation failed."
        }
    }

    // Method to check if mandatory fields are blank or not
    def checkMandatoryFields(def field) {
        if (field == null || field.trim().isEmpty()) {
            steps.echo "Build file parameter is blank"
            return false
        }
        return true
    }
}
