```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EGP Portal - User Administration</title>
    <link rel="stylesheet" href="../styles.css">
</head>
<body>
    <div class="header">
        <h1>Enterprise Governance Portal - User Administration</h1>
    </div>
    <div class="content">
        <c:choose>
            <c:when test="${not empty users}">
                <table>
                    <thead>
                        <tr>
                            <th>Username</th>
                            <th>Role</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td><c:out value="${user.username}"/></td>
                                <td><c:out value="${user.role}"/></td>
                                <td><c:out value="${user.status}"/></td>
                                <td>
                                    <a href="editUser.jsp?username=${user.username}">Edit</a>
                                    <a href="deleteUser.jsp?username=${user.username}" onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>No users found.</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
```

# jenkins/Jenkinsfile
```groovy
pipeline {
    agent any
    
    tools {
        maven 'Maven-3.6.3'
        jdk 'JDK-11'
    }
    
    environment {
        MAVEN_OPTS = '-Xmx2048m'
        JAVA_OPTS = '-Xms1024m -Xmx2048m'
        JBOSS_HOME = '/opt/jboss-as-7.1.1'
        WEBLOGIC_HOME = '/opt/oracle/middleware/wlserver'
    }
    
    stages {
        stage('Checkout') {
            steps {
                script {
                    checkout scm
                }
            }
        }
        stage('Build') {
            steps {
                script {
                    sh 'mvn clean install'
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    sh 'mvn test'
                }
            }
        }
        stage('Package') {
            steps {
                script {
                    sh 'mvn package'
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    deployToServers()
                }
            }
        }
    }
    
    post {
        success {
            script {
                echo 'Build and deployment successful!'
            }
        }
        failure {
            script {
                echo 'Build or deployment failed.'
            }
        }
    }
}

def deployToServers() {
    // Deployment logic for both JBoss and WebLogic
    sh '''
        if [ -d "$JBOSS_HOME" ]; then
            echo "Deploying to JBoss..."
            # JBoss deployment commands
        fi
        
        if [ -d "$WEBLOGIC_HOME" ]; then
            echo "Deploying to WebLogic..."
            # WebLogic deployment commands
        fi
    '''
}
```