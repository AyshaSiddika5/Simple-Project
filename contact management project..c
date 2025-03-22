//some header files
#include<stdio.h>
#include<conio.h>
#include<string.h>

//structure declaration
struct list
{
    char name[20];
    char phone[20];
    char email[30];
    char address[20];
} list, check;  //structure variables


void createContact()   //function for add or create contact
{
    FILE *fp;
    fp = fopen("contact.txt","a");

    system("cls");

    printf("==========================\n");
    printf("\tNEW CONTACT\n");
    printf("==========================\n");
    printf("Enter Name: ");
    fflush(stdin);
    gets(list.name);
    printf("Enter Phone: ");
    fflush(stdin);
    gets(list.phone);
    printf("Enter Email: ");
    fflush(stdin);
    gets(list.email);
    printf("Enter Address: ");
    fflush(stdin);
    gets(list.address);

    fprintf(fp, "%s %s %s %s\n", list.name,list.phone,list.email,list.address);
    fclose(fp);
    printf("====SUCCESFULL====\n\n");

    system("pause");
}

void editContact()    //function for editing contact
{
    system("cls");

    int ch, f=0;
    FILE *fp, *newrec;
    fp = fopen("contact.txt","r");
    newrec = fopen("temp.txt","w");
    printf("==========================\n");
    printf("\tEDIT CONTACT\n");
    printf("==========================\n");
    printf("Enter Name: ");
    fflush(stdin);
    gets(check.name);

    while(fscanf(fp,"%s %s %s %s\n", list.name,list.phone,list.email,list.address) != EOF)
    {
        if(strcmp(check.name,list.name) == 0)
        {
            f=1;
            do
            {
                system("cls");
                printf("==========================\n");
                printf("\tEDIT CONTACT\n");
                printf("==========================\n");
                printf("What you want to edit\n");
                printf("1.Name\n");
                printf("2.Phone\n");
                printf("3.Email\n");
                printf("4.Address\n");
                printf("0.Exit\n");
                printf("==========================\n");
                printf("Enter option : ");
                scanf("%d", &ch);
                switch(ch)
                {

                case 1:
                    printf("Enter new name: ");
                    fflush(stdin);
                    gets(list.name);
                    printf("====SUCCESFULL====\n\n");
                    system("pause");
                    break;
                case 2:
                    printf("Enter new phone: ");
                    fflush(stdin);
                    gets(list.phone);
                    printf("====SUCCESFULL====\n\n");
                    system("pause");
                    break;
                case 3:
                    printf("Enter new email: ");
                    fflush(stdin);
                    gets(list.email);
                    printf("====SUCCESFULL====\n\n");
                    system("pause");
                    break;
                case 4:
                    printf("Enter new address: ");
                    fflush(stdin);
                    gets(list.address);
                    printf("====SUCCESFULL====\n\n");
                    system("pause");
                    break;
                case 0:
                    fprintf(newrec, "%s %s %s %s\n", list.name,list.phone,list.email,list.address);
                    break;
                }

            }
            while(ch != 0);

        }
        else
        {
            fprintf(newrec,"%s %s %s %s\n", list.name, list.phone, list.email, list.address);
        }
    }
    fclose(fp);
    fclose(newrec);
    remove("contact.txt");
    rename("temp.txt","contact.txt");
    if(f == 0)
    {
        printf("Cannot found the name\n\n");

        system("pause");
    }
}

void displayContacts()    //function for displaying the saved contacts
{
    int f=0;
    FILE *fp;
    fp = fopen("contact.txt","r");

    system("cls");

    printf("=================================\n");
    printf("\tDISPLAY CONTACTS\n");
    printf("=================================\n\n\n");

    printf("%-15s%-15s%-15s%-15s\n", "NAME", "PHONE", "EMAIL", "ADDRESS");
    printf("===========================================================\n");
    while(fscanf(fp,"%s %s %s %s\n", list.name, list.phone, list.email, list.address) != EOF)
    {
        f=1;
        printf("%-15s%-15s%-15s%-15s\n", list.name, list.phone, list.email, list.address);
    }
    fclose(fp);
    if(f == 0)
    {
        printf("No record contact\n\n");
        system("pause");
    }

    system("pause");
}

void deleteContacts()    //function for deleting contact
{
    int ch, f=0;
    FILE *fp, *newrec;
    fp = fopen("contact.txt","r");
    newrec = fopen("temp.txt","w");

    system("cls");

    printf("===============================\n");
    printf("\tDELETE CONTACT\n");
    printf("===============================\n");
    printf("Enter Name: ");
    fflush(stdin);
    gets(check.name);

    while(fscanf(fp,"%s %s %s %s\n", list.name,list.phone,list.email,list.address) != EOF)
    {
        if(strcmp(check.name, list.name) == 0)
        {
            f=1;
        }
        else
        {
            fprintf(newrec,"%s %s %s %s\n", list.name, list.phone, list.email, list.address);
        }
    }
    fclose(fp);
    fclose(newrec);
    remove("contact.txt");
    rename("temp.txt","contact.txt");
    if(f == 0)
    {
        printf("Cannot found the name\n\n");
        system("pause");
    }
    else
    {
        printf("====SUCCESFULL====\n\n");

        system("pause");
    }
}

void main()    //main function
{
    int ch;
    do
    {
        system("cls");

        printf("==========================\n");
        printf("\tMAIN MENU\n");
        printf("==========================\n");
        printf("1.Create new contact\n");
        printf("2.Edit contact\n");
        printf("3.Delete contact\n");
        printf("4.Display contact\n");
        printf("0.Exit\n");
        printf("==========================\n\n");
        printf("Enter option : ");
        scanf("%d", &ch);
        switch(ch)
        {
        case 1:
            createContact();
            break;
        case 2:
            editContact();
            break;
        case 3:
            deleteContacts();
            break;
        case 4:
            displayContacts();
            break;
        }
    }
    while(ch != 0);
}
