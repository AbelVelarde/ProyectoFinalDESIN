Name "Instalador de Cliente de Correo"

OutFile "EmailClientInstaller.exe"

InstallDir $PROGRAMFILES\EmailClient

RequestExecutionLevel admin



Page directory
Page instfiles


Section
	SetOutPath $INSTDIR
	
	WriteUninstaller "$INSTDIR\uninstall.exe"
	
	SetOutPath $INSTDIR\app
	
	File /r "..\out\artifacts\ProyectoEmail_jar\*"
	
	SetOutPath $INSTDIR\app
	
	File /r "..\ayuda"
	
	File /r "..\informes"

	File "..\archivos"
	
	File /r "K:\Programas\JavaJDK\jdk-13\bin\java-runtime"
	
	File /r "K:\Programas\JavaJDK\javafx-sdk-13"
	
	CreateShortCut '$DESKTOP\EmailClient.lnk' '$INSTDIR\app\java-runtime\bin\java' '--module-path $INSTDIR\app\javafx-sdk-13\lib --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.web,javafx.base --add-opens=javafx.graphics/javafx.scene=ALL-UNNAMED -jar $INSTDIR\app\ProyectoEmail.jar'
    CreateShortCut '$SMPROGRAMS\EmailClient.lnk' '$INSTDIR\app\java-runtime\bin\java' '--module-path $INSTDIR\app\javafx-sdk-13\lib --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.web,javafx.base --add-opens=javafx.graphics/javafx.scene=ALL-UNNAMED -jar $INSTDIR\app\ProyectoEmail.jar'

	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\EmailClient" "DisplayName" "EmailClient"

	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\EmailClient" "Publisher" "Abel Velarde"
	
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\EmailClient" "UninstallString" "$\"$INSTDIR\uninstall.exe$\""

SectionEnd

Section "uninstall"

	delete "$INSTDIR\uninstall.exe"
	
	DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\EmailClient"
	
	RmDir /r "$INSTDIR\app"
	
	delete "$DESKTOP\EmailClient.lnk"
	delete "$SMPROGRAMS\EmailClient.lnk"
	
	RmDir "$INSTDIR"

SectionEnd

